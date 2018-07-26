package com.coosv.common.utils.json.adapter;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

/**
 * @Description TODO
 * @author fanglei.lu
 * @date 2018年4月26日
 */
public class DateTypeAdapter extends TypeAdapter<Date>{

	private static final String SIMPLE_NAME = "DateTypeAdapter";

	private Class<? extends Date> dateType;
	private DateFormat enUsFormat;
	private DateFormat localFormat;

	public DateTypeAdapter() {
		this(Date.class, DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.US),
				DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT));
	}

	public DateTypeAdapter(String datePattern) {
		this(Date.class, new SimpleDateFormat(datePattern, Locale.US), new SimpleDateFormat(datePattern));
	}

	public DateTypeAdapter(int style) {
		this(Date.class, DateFormat.getDateInstance(style, Locale.US), DateFormat.getDateInstance(style));
	}

	public DateTypeAdapter(int dateStyle, int timeStyle) {
		this(Date.class, DateFormat.getDateTimeInstance(dateStyle, timeStyle, Locale.US),
				DateFormat.getDateTimeInstance(dateStyle, timeStyle));
	}

	public DateTypeAdapter(final Class<? extends Date> dateType, DateFormat enUsFormat, DateFormat localFormat) {
		if (dateType != Date.class && dateType != java.sql.Date.class && dateType != Timestamp.class) {
			throw new IllegalArgumentException("Date type must be one of " + Date.class + ", " + Timestamp.class
					+ ", or " + java.sql.Date.class + " but was " + dateType);
		}
		this.dateType = dateType;
		this.enUsFormat = enUsFormat;
		this.localFormat = localFormat;
	}

	@Override
	public void write(JsonWriter out, Date value) throws IOException {
		
		synchronized (localFormat) {
			String dateFormatAsString = null;
			if(value != null) {
				dateFormatAsString	= enUsFormat.format(value);
			}
			 
			out.value(dateFormatAsString);
		}
	}

	@Override
	public Date read(JsonReader in) throws IOException {
		if (in.peek() != JsonToken.STRING) {
			throw new JsonParseException("The date should be a string value");
		}
		Date date = deserializeToDate(in.nextString());
		if (dateType == Date.class) {
			return date;
		} else if (dateType == Timestamp.class) {
			return new Timestamp(date.getTime());
		} else if (dateType == java.sql.Date.class) {
			return new java.sql.Date(date.getTime());
		} else {
			// This must never happen: dateType is guarded in the primary constructor
			throw new AssertionError();
		}
	}

	private Date deserializeToDate(String s) {
		synchronized (localFormat) {
			try {
				return localFormat.parse(s);
			} catch (ParseException ignored) {
			}
			try {
				return enUsFormat.parse(s);
			} catch (ParseException ignored) {
			}
			try {
				return ISO8601Utils.parse(s, new ParsePosition(0));
			} catch (ParseException e) {
				throw new JsonSyntaxException(s, e);
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(SIMPLE_NAME);
		sb.append('(').append(localFormat.getClass().getSimpleName()).append(')');
		return sb.toString();
	}
}
