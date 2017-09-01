package foo;

import static com.google.common.truth.Truth.assertThat;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;

@RunWith(JUnit4.class)
public class FooTest {
  @Test
  public void test1() {
    assertThat(new Foo().foo()).contains("cat");
  }
}
