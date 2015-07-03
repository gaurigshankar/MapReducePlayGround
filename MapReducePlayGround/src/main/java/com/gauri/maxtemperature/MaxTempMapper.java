package com.gauri.maxtemperature;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MaxTempMapper extends Mapper<Object,Text,Text,IntWritable> {
	private static final int MISSING = 9999;
	@Override
	protected void map(Object key, Text value,
			Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		String year = line.substring(15, 19);
		
		int airTemp;
		if(line.charAt(87) == '+'){
			airTemp = Integer.parseInt(line.substring(88, 92));
		}
		else{
			airTemp = Integer.parseInt(line.substring(87, 92));
		}
		String quality = line.substring(92,93);
		
		if(airTemp!=MISSING && quality.matches("[01459]")){
			context.write(new Text(year), new IntWritable(airTemp));
		}
		
	}
}
