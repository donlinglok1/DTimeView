package org.darkdusk.dtimeview.demo;

import org.darkdusk.dtimeview.DTimeView;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Act_Main extends Activity {
	private transient Activity context;
	private transient Context baseContext;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = Act_Main.this;
		baseContext = context.getApplicationContext();

		setContentView(R.layout.act_main);

		// main usage code
		final RelativeLayout layoutTimePicker = (RelativeLayout) findViewById(R.id.layoutTimePicker);
		final DTimeView dTimePicker = new DTimeView(context);
		layoutTimePicker.addView(dTimePicker);
		Log.e("", "" + dTimePicker.getDate());

		// optional -set the Format of Timer View
		dTimePicker.setDateTimeFormat("HH:mm aa");

		// optional -customize the textview
		final TextView[] textViews = dTimePicker.getAllTextViews();
		for (final TextView textView : textViews) {
			textView.setTextSize(30);
			textView.setTextColor(baseContext.getResources().getColor(
					R.color.white));
		}

		// // optional -customize the button
		// // text button
		// final Button[] buttons = dTimePicker.getAllButtons();
		// for (final Button button : buttons) {
		// button.setTextSize(30);
		// button.setTextColor(baseContext.getResources().getColor(
		// R.color.white));
		// button.setBackgroundResource(R.drawable.selector_transparent);
		// }

		// optional -customize the button
		// image button
		final Drawable arrow_up = baseContext.getResources().getDrawable(
				R.drawable.arrow_up);
		final Drawable arrow_down = baseContext.getResources().getDrawable(
				R.drawable.arrow_down);
		final Button[] buttons = dTimePicker.getAllButtons();
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setText("");
			buttons[i].setBackgroundResource(R.drawable.selector_transparent);
			if (i % 2 == 0) {
				buttons[i].setCompoundDrawablesWithIntrinsicBounds(null, null,
						null, arrow_up);
			} else {
				buttons[i].setCompoundDrawablesWithIntrinsicBounds(null,
						arrow_down, null, null);
			}
			buttons[i].setCompoundDrawablePadding(-30);
		}

		// optional -customize the layout
		// move am-pm to top
		final LinearLayout[] layouts = dTimePicker.getAllLinearLayout();
		layouts[0].removeView(layouts[layouts.length - 1]);
		layouts[0].addView(layouts[layouts.length - 1], 0);
	}
}
