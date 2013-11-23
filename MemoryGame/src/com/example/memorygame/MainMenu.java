package com.example.memorygame;

import java.util.List;

import android.preference.PreferenceManager;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class MainMenu extends SherlockActivity {

	Boolean continueMusic = true;// = true;

	Boolean yo;

	Button btPlayNow, btInstructions, btOptions, btHighScores;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* Custom Themes */
		themeUtils.onActivityCreateSetTheme(this);

		setContentView(R.layout.activity_main_menu);
		int hello = themeUtils.getcTheme();
		if (hello == 0) {
			getSupportActionBar().setBackgroundDrawable(
					getResources().getDrawable(R.drawable.backgroundactionbar));
		}
		if (hello == 1) {
			getSupportActionBar().setBackgroundDrawable(
					getResources().getDrawable(R.drawable.blue_background));
		}
		SharedPreferences sharedPrefs = getSharedPreferences(null, MODE_PRIVATE);
		yo = sharedPrefs.getBoolean("tgref", true);

		btPlayNow = (Button) findViewById(R.id.button_play);
		btInstructions = (Button) findViewById(R.id.button_instructions);
		btOptions = (Button) findViewById(R.id.button_options);
		btHighScores = (Button) findViewById(R.id.button_high_score);

		btPlayNow.setBackgroundColor(Color.TRANSPARENT);
		btInstructions.setBackgroundColor(Color.TRANSPARENT);
		btOptions.setBackgroundColor(Color.TRANSPARENT);
		btHighScores.setBackgroundColor(Color.TRANSPARENT);

	}

	@Override
	protected void onPause() {
		super.onPause();
		if (!continueMusic) {
			MusicManager.pause();
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
		if (yo) // if (yo) may be enough, not sure
		{
			continueMusic = false;
			MusicManager.start(this, MusicManager.MUSIC_MENU);
		}

		else {
			MusicManager.pause();
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 100) {
			startActivity(new Intent(this, MainMenu.class));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(this).inflate(R.menu.main_menu, menu);

		return (super.onCreateOptionsMenu(menu));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId = item.getItemId();
		if (itemId == R.id.action_settings) {
			abOptions();
			return (true);
		} else if (itemId == R.id.action_info) {
			abInfo();
			return (true);
		}
		return (super.onOptionsItemSelected(item));
	}

	// Methods for buttons which are referenced in activity_main_menu.xml with
	// android:onClick
	// OnClickListeners may be more appropriate for situations like fragments
	public void btPlayNow(View btPlayNow) {
		continueMusic = true;
		startActivity(new Intent(this, PlayGame.class));

	}

	public void btInstructions(View btInstructions) {

		continueMusic = true;
		startActivity(new Intent(this, Instructions.class));

	}

	public void btOptions(View btOptions) {
		continueMusic = true;
		startActivityForResult(new Intent(this, Options.class), 100);

	}

	public void btHighScores(View btHighScores) {

		continueMusic = true;
		startActivity(new Intent(this, HighScores.class));

	}

	// Options activity launched from the actionBar
	public void abOptions() {
		continueMusic = true;
		startActivity(new Intent(this, Options.class));

	}

	public void abInfo() {
		continueMusic = true;
		startActivity(new Intent(this, About.class));

	}

}
