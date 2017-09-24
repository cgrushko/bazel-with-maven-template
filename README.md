A turn-key template repository to develop Java code using [Bazel](http://bazel.build),
with prepopulated common dependencies.

# Try it

```bash
# Run the test, which first builds exatly everything needed
$ bazel test //src/test/java/foo:FooTest

# Or just build
$ bazel test //src/main/java/foo:Foo
```

This compiles `./Foo.java` using the `java_library` defined in `./BUILD`.

# Created using

Assuming you're in the root of this repository,

    ROOT=$(pwd)
    wget https://github.com/johnynek/bazel-deps/archive/master.zip
    unzip master.zip
    pushd bazel-deps-master
    bazel build //src/scala/com/github/johnynek/bazel_deps:parseproject_deploy.jar  # should take around 1 minute
    ./gen_maven_deps.sh generate -r "$ROOT" -d maven_deps.yaml -s thirdparty/workspace.bzl
    popd

# Adding New Dependencies

Suppose you want to use `org.reactivestreams:reactive-streams:jar:1.0.1`.
Add the following to the end of `maven_deps.yaml` (indentation is important):

```
  org.reactivestreams:
    reactive-streams:
      version: "1.0.1"
      lang: java
```

Then follow the instructions in "Created using", above.
After running, you should see a new file named `thirdparty/jvm/org/reactivestreams/BUILD`. 

# Things I'm unhappy with in this example

1. "thirdparty" should be named "third_party", which is blocked on https://github.com/bazelbuild/bazel/issues/3639
2. We shouldn't use `bind()` - instead, we should reference the maven jars directly. We actually don't even need a `java_library` in thirdparty/ - we can use `java_import` directly on the jars we get from `maven_jar`.
3. The `//thirdparty:load.bzl` file is unnecessary - bazel-deps should inline it into `//thirdparty:workspace.bzl`. (https://github.com/johnynek/bazel-deps/issues/60)
4. Configuring annotation prcessors is manualy (https://github.com/johnynek/bazel-deps/issues/62)
5. Test-only dependencies (such as //thirdparty/jvm/junit:junit) should be marked have `testonly = 1`, lest they mistakenly end up in production code.
