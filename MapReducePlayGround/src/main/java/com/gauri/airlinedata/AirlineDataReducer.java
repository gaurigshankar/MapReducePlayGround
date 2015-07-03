package com.gauri.airlinedata;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AirlineDataReducer extends Reducer<Text, IntWritable, Text, IntWritable>{

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Context context)
			throws IOException, InterruptedException {
		IntWritable finalValue = null;
		for (IntWritable value : values){
			finalValue = value;
		}
		
		context.write(key, finalValue);
	}
}
