package org.com.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import org.com.driver.statics.DriverUtils;
import org.com.report.Logger;
import org.openqa.selenium.OutputType;
import org.testng.asserts.IAssert;
import org.testng.collections.Maps;
import org.testng.asserts.Assertion;

import java.io.ByteArrayInputStream;
import java.util.Map;
import java.util.UUID;

/**
 * When an assertion fails, don't throw an exception but record the failure. Calling {@code
 * assertAll()} will cause an exception to be thrown if at least one assertion failed.
 */
public class SoftAssertion extends Assertion {

    public void assertAll() {
        assertAll(null);
    }

    public void assertAll(String message) {
        if (!m_errors.isEmpty()) {
            Allure.step(message + " - Total check point failed: " + m_errors.size(), Status.FAILED);
            Map<AssertionError, IAssert<?>> tempErrors = m_errors;
            m_errors = Maps.newLinkedHashMap();
            throw new AssertionError(tempErrors);
        } else {
            Logger.info(message);
        }
    }

    @Override
    protected void doAssert(IAssert<?> a) {
        onBeforeAssert(a);
        try {
            a.doAssert();
            onAssertSuccess(a);
            String message = "%s ↪ PASSED - Expected: %s - Actual: %s";
            Logger.info(String.format(message, a.getMessage(), a.getActual(), a.getExpected()));
        } catch (AssertionError ex) {
            onAssertFailure(a, ex);
            m_errors.put(ex, a);
            Logger.info(a.getMessage() + "↪ FAILED - Expected: " + a.getExpected() + " - Actual: " + a.getActual(), Status.FAILED);
        } finally {
            onAfterAssert(a);
        }
    }

    private Map<AssertionError, IAssert<?>> m_errors = Maps.newLinkedHashMap();
}
