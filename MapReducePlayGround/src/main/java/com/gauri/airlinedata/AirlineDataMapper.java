package com.gauri.airlinedata;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AirlineDataMapper extends Mapper<Object, Text, Text, IntWritable> {

	@Override
	protected void map(Object key, Text value,
			Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		String[] fields = line.split(",");
		Integer arrivalDelayInMinutes = 0;
		if(!fields[14].equalsIgnoreCase("NA"))
		arrivalDelayInMinutes = Integer.parseInt(fields[14]);
		String originAirport = fields[16];
		String destAirport = fields[17];
		String flightDate = fields[0]+"-"+fields[1]+"-"+fields[2];
		if(arrivalDelayInMinutes>=30 && originAirport.equalsIgnoreCase("SAN") && destAirport.equalsIgnoreCase("SFO")){
			
			context.write(new Text(flightDate), new IntWritable(arrivalDelayInMinutes));
		}
	}
}
