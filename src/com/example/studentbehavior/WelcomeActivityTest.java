package com.example.studentbehavior;

import static org.junit.Assert.*;

import org.junit.Test;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

public class WelcomeActivityTest {

	@Test
	public void test() {
		WelcomeActivity activity = new WelcomeActivity();
		assertNotNull(activity.username);
	}
}
