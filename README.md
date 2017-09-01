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
