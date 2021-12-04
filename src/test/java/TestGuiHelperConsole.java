import consoleforgui.HelperConsole;
import org.junit.Test;


public class TestGuiHelperConsole {

    @Test(timeout = 500)
    public void testValidGenderTrue() {
        assert HelperConsole.validGender("M");
    }

    @Test(timeout = 500)
    public void testValidGenderFalse() {
        assert !HelperConsole.validGender("1");
    }

    @Test(timeout = 500)
    public void testValidHeightTrue() {
        assert HelperConsole.validHeight("1.90");
    }

    @Test(timeout = 500)
    public void testValidHeightFalse1() {
        assert !HelperConsole.validHeight("5, 4");
    }

    @Test(timeout = 500)
    public void testValidHeightFalse2() {
        assert !HelperConsole.validHeight("4.0");
    }

    @Test(timeout = 500)
    public void testValidWeightTrue() {
        assert HelperConsole.validWeight("80");
    }

    @Test(timeout = 500)
    public void testValidWeightFalse1() {
        assert !HelperConsole.validWeight("10 kg");
    }

    @Test(timeout = 500)
    public void testValidWeightFalse2() {
        assert !HelperConsole.validWeight("0");
    }

    @Test(timeout = 500)
    public void testValidAgeTrue() {
        assert HelperConsole.validAge("80");
    }

    @Test(timeout = 500)
    public void testValidAgeFalse1() {
        assert !HelperConsole.validAge("10 years old");
    }

    @Test(timeout = 500)
    public void testValidAgeFalse2() {
        assert !HelperConsole.validAge("-1");
    }


}
