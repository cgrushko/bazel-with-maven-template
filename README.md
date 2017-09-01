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

    wget https://github.com/johnynek/bazel-deps/archive/master.zip
    unzip master.zip
    ./gen_maven_deps.sh generate -r $PATH_TO_bazel-with-maven-template -d maven_deps.yaml -s 3rdparty/workspace.bzl

This results in a 3rdparty/ directory, which we'll rename to thirdparty/ (because Python can't
handle such directory, see https://github.com/johnynek/bazel-deps/issues/59)

    mv 3rdparty/* thirdparty/
    # On OS X (i.e., BSD sed):
    find thirdparty/ -name BUILD | xargs sed -i '' -e 's%3rdparty%thirdparty%g'
    # On Linux (i.e., GNU sed):
    find thirdparty/ -name BUILD | xargs sed -i 's%3rdparty%thirdparty%g'

# Things I'm unhappy with in this example

1. "thirdparty" should be named "third_party", which is blocked on https://github.com/bazelbuild/bazel/issues/3639
2. We shouldn't use `bind()` - instead, we should reference the maven jars directly. We actually don't even need a `java_library` in thirdparty/ - we can use `java_import` directly on the jars we get from `maven_jar`.
3. The `//thirdparty:load.bzl` file is unnecessary - bazel-deps should inline it into `//thirdparty:workspace.bzl`. (https://github.com/johnynek/bazel-deps/issues/60)
4. Configuring annotation prcessors is manualy (https://github.com/johnynek/bazel-deps/issues/62)
5. Test-only dependencies (such as //thirdparty/jvm/junit:junit) should be marked have `testonly = 1`, lest they mistakenly end up in production code.
