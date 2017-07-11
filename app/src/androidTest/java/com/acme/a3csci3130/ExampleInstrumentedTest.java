package com.acme.a3csci3130;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

/**
 * Instrumentation tests, which will execute on an Android device.
 * Thread.sleep() is used because every test other than create will fail
 * if the emulator is just being loaded for the first time;
 *
 * @author Joshua Morash
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    String businessId = "123456789";
    String name = "test";
    String primaryBusiness = "Fisher";
    String address = "test";
    String provinceOrTerritory = "NS";


    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);


    @Test
    public void test_submitInfoButton() throws Exception {
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.name)).perform(replaceText(name));
        onView(withId(R.id.address)).perform(replaceText(address));
        onView(withId(R.id.provinceOrTerritory)).perform(replaceText(provinceOrTerritory));
        onView(withId(R.id.primaryBusiness)).perform(replaceText(primaryBusiness));
        onView(withId(R.id.businessId)).perform(replaceText(businessId));
        onView(withId(R.id.submitButton)).perform(click());
    }

    @Test
    public void test_updateContact() throws Exception {
        Thread.sleep(5000);
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.name)).perform(replaceText("New Name"));
        onView(withId(R.id.updateButton)).perform(click());
    }

    @Test
    public void test_eraseContact() throws Exception {
        Thread.sleep(5000);
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.deleteButton)).perform(click());
    }

    @Test
    public void test_readContact() throws Exception {
        Thread.sleep(5000);
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.name)).check(matches(notNullValue()));
        onView(withId(R.id.primaryBusiness)).check(matches(notNullValue()));
        onView(withId(R.id.businessId)).check(matches(notNullValue()));
    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.

        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.acme.a3csci3130", appContext.getPackageName());
    }
}
