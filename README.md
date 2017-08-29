Created using:

    wget https://github.com/johnynek/bazel-deps/archive/master.zip
    unzip master.zip
    ./gen_maven_deps.sh generate -r $PATH_TO_bazel-with-maven-template -d maven_deps.yaml -s 3rdparty/workspace.bzl

This results in a 3rdparty/ directory, which we'll rename to third_party/ (because Python can't handle such directory, see https://github.com/johnynek/bazel-deps/issues/59)

    mv 3rdparty/* third_party/
    # On OS X (i.e., BSD sed):
    find third_party/ -name BUILD | xargs sed -i '' -e 's%3rdparty%third_party%g'
    # On Linux (i.e., GNU sed):
    find third_party/ -name BUILD | xargs sed -i 's%3rdparty%third_party%g'
