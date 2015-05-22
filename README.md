# DTimeView
Android Date Time Picker
![alt tag](https://raw.githubusercontent.com/donlinglok/DTimeView/master/DTimeViewDemo/assets/device-2015-05-18-104355.png)
# main usage code
```sh
final RelativeLayout layoutTimePicker = (RelativeLayout) findViewById(R.id.layoutTimePicker);
final DTimeView dTimePicker = new DTimeView(context);
layoutTimePicker.addView(dTimePicker);
Log.e("", "" + dTimePicker.getDate());
```
# optional -set the Format of Timer View
```sh
dTimePicker.setDateTimeFormat("HH:mm aa");
```
# optional -customize the textview
![alt tag](https://raw.githubusercontent.com/donlinglok/DTimeView/master/DTimeViewDemo/assets/device-2015-05-18-105004.png)
```sh
final TextView[] textViews = dTimePicker.getAllTextViews();
for (final TextView textView : textViews) {
	textView.setTextSize(30);
	textView.setTextColor(baseContext.getResources().getColor(
		R.color.white));
}
```
# optional -customize the button -text button
![alt tag](https://raw.githubusercontent.com/donlinglok/DTimeView/master/DTimeViewDemo/assets/device-2015-05-18-105004.png)
```sh
final Button[] buttons = dTimePicker.getAllButtons();
for (final Button button : buttons) {
	button.setTextSize(30);
	button.setTextColor(baseContext.getResources().getColor(
		R.color.white));
	button.setBackgroundResource(R.drawable.selector_transparent);
 }
```
# optional -customize the button -image button
![alt tag](https://raw.githubusercontent.com/donlinglok/DTimeView/master/DTimeViewDemo/assets/device-2015-05-18-104327.png)
```sh
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
```
# optional -customize the layout -move am-pm to top
![alt tag](https://raw.githubusercontent.com/donlinglok/DTimeView/master/DTimeViewDemo/assets/device-2015-05-18-114517.png)
```sh
final LinearLayout[] layouts = dTimePicker.getAllLinearLayout();
layouts[0].removeView(layouts[layouts.length - 1]);
layouts[0].addView(layouts[layouts.length - 1], 0);
```
