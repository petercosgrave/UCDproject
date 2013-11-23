package com.example.memorygame;

import android.app.Activity;

import android.content.Intent;


 public class themeUtils

{

private static int cTheme;



public final static int BLACK = 1;

public final static int BLUE = 0;

public static void changeToTheme(Activity activity, int theme)

{

setcTheme(theme);

activity.finish();



activity.startActivity(new Intent(activity, activity.getClass()));


 }

public static void onActivityCreateSetTheme(Activity activity)

{

switch (getcTheme())

{

default:

case BLACK:

activity.setTheme(R.style.CustomTheme);

break;

case BLUE:

activity.setTheme(R.style.BlueTheme);

break;

}

}

public static int getcTheme() {
	return cTheme;
}

public static int setcTheme(int cTheme) {
	themeUtils.cTheme = cTheme;
	return cTheme;
}

}
