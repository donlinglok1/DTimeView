package org.darkdusk.dtimeview;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.os.Handler;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DTimeView extends FrameLayout {
	private transient final String TAG = this.getClass().getName();

	private transient String dateTimeFormat = "yyyy/MM/dd HH:mm:ss aa";

	public String setDateTimeFormat(final String dateTimeFormat) {
		this.dateTimeFormat = dateTimeFormat;
		initController(dateTimeFormat);
		return this.dateTimeFormat;
	}

	private transient final Calendar calendar = Calendar.getInstance();

	public Date getDate() {
		return calendar.getTime();
	}

	public Date setDate(final Date date) {
		calendar.setTime(date);
		update(calendar);
		return calendar.getTime();
	}

	public Date setDate(final int field, final int value) {
		calendar.add(field, value);
		update(calendar);
		return calendar.getTime();
	}

	private final transient Handler handler = new Handler();
	private transient boolean isTikTok = true;

	public void setTikTok(final boolean isTikTok) {
		this.isTikTok = isTikTok;
		if (isTikTok) {
			handler.post(tiktokRunnable);
		} else {
			handler.removeCallbacks(tiktokRunnable);
		}
	}

	public void setTikTok(final boolean isTikTok, final Runnable runnable) {
		this.isTikTok = isTikTok;
		if (isTikTok) {
			handler.post(runnable);
		} else {
			handler.removeCallbacks(runnable);
		}
	}

	public transient Runnable tiktokRunnable = new Runnable() {
		@Override
		public void run() {
			setDate(Calendar.SECOND, 1);

			if (isTikTok) {
				handler.postDelayed(tiktokRunnable, 1000 * 1);
			}
		}
	};

	public DTimeView(final Context context) {
		super(context);
		LayoutInflater.from(context).inflate(R.layout.dtimepickers, this);

		initController(dateTimeFormat);
		update(calendar);
		setTikTok(true);

		Log.e(TAG,
				"DTimePicker is started. Default Date is = "
						+ calendar.getTime());
	}

	public DTimeView(final Context context, final Date date) {
		super(context);
		calendar.setTime(date);
		LayoutInflater.from(context).inflate(R.layout.dtimepickers, this);

		initController(dateTimeFormat);
		update(calendar);
		setTikTok(true);

		Log.e(TAG,
				"DTimePicker is started. Default Date is = "
						+ calendar.getTime());
	}

	public DTimeView(final Context context, final AttributeSet attrs) {
		super(context, attrs);
		Log.e(TAG, "XML SUCKS! JAVA RULES!!");
	}

	private void update(final Calendar calendar) {
		try {
			((TextView) findViewById(R.id.txtYear)).setText(DateFormat.format(
					fomatYear, calendar));
			((TextView) findViewById(R.id.txtMonth)).setText(DateFormat.format(
					fomatMonth, calendar));
			((TextView) findViewById(R.id.txtDay)).setText(DateFormat.format(
					fomatDay, calendar));
			((TextView) findViewById(R.id.txtHour)).setText(DateFormat.format(
					fomatHour, calendar));
			((TextView) findViewById(R.id.txtMinutes)).setText(DateFormat
					.format(fomatMinute, calendar));
			((TextView) findViewById(R.id.txtSecond)).setText(DateFormat
					.format(fomatSecond, calendar));
			((TextView) findViewById(R.id.txtAmPm)).setText(DateFormat.format(
					fomatAMPM, calendar));
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
	}

	private transient final static String EMPTY = "";
	private transient final static String YEAR4 = "yyyy";
	private transient final static String YEAR2 = "yy";
	private transient final static String MONTH = "MM";
	private transient final static String DAY_MONTH = "dd";
	private transient final static String DAY_YEAR = "DD";
	private transient final static String HOUR24 = "HH";
	private transient final static String HOUR12 = "hh";
	private transient final static String MINUTE = "mm";
	private transient final static String SECOND = "ss";
	private transient final static String AMPM = "aa";
	private transient final static String SLASH = "/";
	private transient final static String SPACE = " ";
	private transient final static String COLON = ":";

	private transient String fomatYear = YEAR4;
	private transient String fomatMonth = MONTH;
	private transient String fomatDay = DAY_MONTH;
	private transient String fomatHour = HOUR24;
	private transient String fomatMinute = MINUTE;
	private transient String fomatSecond = SECOND;
	private transient String fomatAMPM = AMPM;

	private void setVisibility(final View view, final int visibility) {
		if (view.getVisibility() != visibility) {
			view.setVisibility(visibility);
		}
	}

	private void initController(final String format) {
		if (!isInEditMode()) {
			String dateTimeFormat = format;
			if (dateTimeFormat.contains(YEAR4)) {
				fomatYear = YEAR4;
				dateTimeFormat = dateTimeFormat.replace(YEAR4, EMPTY);
				setVisibility(findViewById(R.id.layoutYear), View.VISIBLE);
			} else if (dateTimeFormat.contains(YEAR2)) {
				fomatYear = YEAR2;
				dateTimeFormat = dateTimeFormat.replace(YEAR2, EMPTY);
				setVisibility(findViewById(R.id.layoutYear), View.VISIBLE);
			} else {
				fomatYear = EMPTY;
				setVisibility(findViewById(R.id.layoutYear), View.GONE);
			}

			if (dateTimeFormat.contains(SLASH)) {
				dateTimeFormat = dateTimeFormat.replaceFirst(SLASH, EMPTY);
				setVisibility(findViewById(R.id.txtSlash1), View.VISIBLE);
			} else {
				setVisibility(findViewById(R.id.txtSlash1), View.GONE);
			}

			if (dateTimeFormat.contains(MONTH)) {
				fomatMonth = MONTH;
				dateTimeFormat = dateTimeFormat.replace(MONTH, EMPTY);
				setVisibility(findViewById(R.id.layoutMonth), View.VISIBLE);
			} else {
				fomatMonth = EMPTY;
				setVisibility(findViewById(R.id.layoutMonth), View.GONE);
			}

			if (dateTimeFormat.contains(SLASH)) {
				dateTimeFormat = dateTimeFormat.replaceFirst(SLASH, EMPTY);
				setVisibility(findViewById(R.id.txtSlash2), View.VISIBLE);
			} else {
				setVisibility(findViewById(R.id.txtSlash2), View.GONE);
			}

			if (dateTimeFormat.contains(DAY_MONTH)) {
				fomatDay = DAY_MONTH;
				dateTimeFormat = dateTimeFormat.replace(DAY_MONTH, EMPTY);
				setVisibility(findViewById(R.id.layoutDay), View.VISIBLE);
			} else if (dateTimeFormat.contains(DAY_YEAR)) {
				fomatDay = DAY_YEAR;
				dateTimeFormat = dateTimeFormat.replace(DAY_YEAR, EMPTY);
				setVisibility(findViewById(R.id.layoutDay), View.VISIBLE);
			} else {
				fomatDay = EMPTY;
				setVisibility(findViewById(R.id.layoutDay), View.GONE);
			}

			if (dateTimeFormat.contains(SPACE)) {
				dateTimeFormat = dateTimeFormat.replaceFirst(SPACE, EMPTY);
				setVisibility(findViewById(R.id.txtSpace1), View.VISIBLE);
			} else {
				setVisibility(findViewById(R.id.txtSpace1), View.GONE);
			}

			if (dateTimeFormat.contains(HOUR24)) {
				fomatHour = HOUR24;
				dateTimeFormat = dateTimeFormat.replace(HOUR24, EMPTY);
				setVisibility(findViewById(R.id.layoutHour), View.VISIBLE);
			} else if (dateTimeFormat.contains(HOUR12)) {
				fomatHour = HOUR12;
				dateTimeFormat = dateTimeFormat.replace(HOUR12, EMPTY);
				setVisibility(findViewById(R.id.layoutHour), View.VISIBLE);
			} else {
				fomatHour = EMPTY;
				setVisibility(findViewById(R.id.layoutHour), View.GONE);
			}

			if (dateTimeFormat.contains(COLON)) {
				dateTimeFormat = dateTimeFormat.replaceFirst(COLON, EMPTY);
				setVisibility(findViewById(R.id.txtColon1), View.VISIBLE);
			} else {
				setVisibility(findViewById(R.id.txtColon1), View.GONE);
			}

			if (dateTimeFormat.contains(MINUTE)) {
				fomatMinute = MINUTE;
				dateTimeFormat = dateTimeFormat.replace(MINUTE, EMPTY);
				setVisibility(findViewById(R.id.layoutMinutes), View.VISIBLE);
			} else {
				fomatMinute = EMPTY;
				setVisibility(findViewById(R.id.layoutMinutes), View.GONE);
			}

			if (dateTimeFormat.contains(COLON)) {
				dateTimeFormat = dateTimeFormat.replaceFirst(COLON, EMPTY);
				setVisibility(findViewById(R.id.txtColon2), View.VISIBLE);
			} else {
				setVisibility(findViewById(R.id.txtColon2), View.GONE);
			}

			if (dateTimeFormat.contains(SECOND)) {
				fomatSecond = SECOND;
				dateTimeFormat = dateTimeFormat.replace(SECOND, EMPTY);
				setVisibility(findViewById(R.id.layoutSecond), View.VISIBLE);
			} else {
				fomatSecond = EMPTY;
				setVisibility(findViewById(R.id.layoutSecond), View.GONE);
			}

			if (dateTimeFormat.contains(SPACE)) {
				dateTimeFormat = dateTimeFormat.replaceFirst(SPACE, EMPTY);
				setVisibility(findViewById(R.id.txtSpace2), View.VISIBLE);
			} else {
				setVisibility(findViewById(R.id.txtSpace2), View.GONE);
			}

			if (dateTimeFormat.contains(AMPM)) {
				fomatAMPM = AMPM;
				dateTimeFormat = dateTimeFormat.replace(AMPM, EMPTY);
				setVisibility(findViewById(R.id.layoutAmPm), View.VISIBLE);
			} else {
				fomatAMPM = EMPTY;
				setVisibility(findViewById(R.id.layoutAmPm), View.GONE);
			}

			if (findViewById(R.id.layoutYear).getVisibility() == View.VISIBLE) {
				((Button) findViewById(R.id.btnYearPlus))
						.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(final View arg0) {
								setDate(Calendar.YEAR, 1);
							}
						});
				((Button) findViewById(R.id.btnYearMinus))
						.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(final View arg0) {
								setDate(Calendar.YEAR, -1);
							}
						});
			}

			if (findViewById(R.id.layoutMonth).getVisibility() == View.VISIBLE) {
				((Button) findViewById(R.id.btnMonthPlus))
						.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(final View arg0) {
								setDate(Calendar.MONTH, 1);
							}
						});
				((Button) findViewById(R.id.btnMonthMinus))
						.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(final View arg0) {
								setDate(Calendar.MONTH, -1);
							}
						});
			}

			if (findViewById(R.id.layoutDay).getVisibility() == View.VISIBLE) {
				((Button) findViewById(R.id.btnDayPlus))
						.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(final View arg0) {
								setDate(Calendar.DAY_OF_MONTH, 1);
							}
						});
				((Button) findViewById(R.id.btnDayMinus))
						.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(final View arg0) {
								setDate(Calendar.DAY_OF_MONTH, -1);
							}
						});
			}

			if (findViewById(R.id.layoutHour).getVisibility() == View.VISIBLE) {
				((Button) findViewById(R.id.btnHourPlus))
						.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(final View arg0) {
								setDate(Calendar.HOUR_OF_DAY, 1);
							}
						});
				((Button) findViewById(R.id.btnHourMinus))
						.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(final View arg0) {
								setDate(Calendar.HOUR_OF_DAY, -1);
							}
						});
			}

			if (findViewById(R.id.layoutMinutes).getVisibility() == View.VISIBLE) {
				((Button) findViewById(R.id.btnMinutesPlus))
						.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(final View arg0) {
								setDate(Calendar.MINUTE, 1);
							}
						});
				((Button) findViewById(R.id.btnMinutesMinus))
						.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(final View arg0) {
								setDate(Calendar.MINUTE, -1);
							}
						});
			}

			if (findViewById(R.id.layoutSecond).getVisibility() == View.VISIBLE) {
				((Button) findViewById(R.id.btnSecondPlus))
						.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(final View arg0) {
								setDate(Calendar.SECOND, 1);
							}
						});
				((Button) findViewById(R.id.btnSecondMinus))
						.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(final View arg0) {
								setDate(Calendar.SECOND, -1);
							}
						});
			}

			if (findViewById(R.id.layoutAmPm).getVisibility() == View.VISIBLE) {
				((Button) findViewById(R.id.btnAmPmPlus))
						.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(final View arg0) {
								if (calendar.get(Calendar.AM_PM) == Calendar.AM) {
									setDate(Calendar.HOUR, 12);
								} else {
									setDate(Calendar.HOUR, -12);
								}
							}
						});
				((Button) findViewById(R.id.btnAmPmMinus))
						.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(final View arg0) {
								if (calendar.get(Calendar.AM_PM) == Calendar.AM) {
									setDate(Calendar.HOUR, 12);
								} else {
									setDate(Calendar.HOUR, -12);
								}
							}
						});
			}
		}
	}

	public TextView[] getAllTextViews() {
		final ArrayList<TextView> result = new ArrayList<TextView>();

		result.add((TextView) findViewById(R.id.txtYear));
		result.add((TextView) findViewById(R.id.txtSlash1));
		result.add((TextView) findViewById(R.id.txtMonth));
		result.add((TextView) findViewById(R.id.txtSlash2));
		result.add((TextView) findViewById(R.id.txtDay));
		result.add((TextView) findViewById(R.id.txtSpace1));
		result.add((TextView) findViewById(R.id.txtHour));
		result.add((TextView) findViewById(R.id.txtColon1));
		result.add((TextView) findViewById(R.id.txtMinutes));
		result.add((TextView) findViewById(R.id.txtColon2));
		result.add((TextView) findViewById(R.id.txtSecond));
		result.add((TextView) findViewById(R.id.txtSpace2));
		result.add((TextView) findViewById(R.id.txtAmPm));

		return result.toArray(new TextView[result.size()]);
	}

	public Button[] getAllButtons() {
		final ArrayList<Button> result = new ArrayList<Button>();

		result.add((Button) findViewById(R.id.btnYearPlus));
		result.add((Button) findViewById(R.id.btnYearMinus));
		result.add((Button) findViewById(R.id.btnMonthPlus));
		result.add((Button) findViewById(R.id.btnMonthMinus));
		result.add((Button) findViewById(R.id.btnDayPlus));
		result.add((Button) findViewById(R.id.btnDayMinus));
		result.add((Button) findViewById(R.id.btnHourPlus));
		result.add((Button) findViewById(R.id.btnHourMinus));
		result.add((Button) findViewById(R.id.btnMinutesPlus));
		result.add((Button) findViewById(R.id.btnMinutesMinus));
		result.add((Button) findViewById(R.id.btnSecondPlus));
		result.add((Button) findViewById(R.id.btnSecondMinus));
		result.add((Button) findViewById(R.id.btnAmPmPlus));
		result.add((Button) findViewById(R.id.btnAmPmMinus));

		return result.toArray(new Button[result.size()]);
	}

	public LinearLayout[] getAllLinearLayout() {
		final ArrayList<LinearLayout> result = new ArrayList<LinearLayout>();

		result.add((LinearLayout) findViewById(R.id.layoutRoot));
		result.add((LinearLayout) findViewById(R.id.layoutYear));
		result.add((LinearLayout) findViewById(R.id.layoutMonth));
		result.add((LinearLayout) findViewById(R.id.layoutDay));
		result.add((LinearLayout) findViewById(R.id.layoutHour));
		result.add((LinearLayout) findViewById(R.id.layoutMinutes));
		result.add((LinearLayout) findViewById(R.id.layoutSecond));
		result.add((LinearLayout) findViewById(R.id.layoutAmPm));

		return result.toArray(new LinearLayout[result.size()]);
	}
}
