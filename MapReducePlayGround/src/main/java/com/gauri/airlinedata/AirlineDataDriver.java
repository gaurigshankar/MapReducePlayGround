package com.gauri.airlinedata;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class AirlineDataDriver {
//CSV dat from http://stat-computing.org/dataexpo/2009/
	public static void main(String[] args) {
		try {
			Job job = new Job(new Configuration() ,"Airline Data");
			
			
			
			job.setMapperClass(AirlineDataMapper.class);
			job.setReducerClass(AirlineDataReducer.class);
			
			job.setInputFormatClass(TextInputFormat.class);
			job.setOutputFormatClass(TextOutputFormat.class);
			
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(IntWritable.class);
			
			FileInputFormat.setInputPaths(job, new Path(args[0]));
			FileOutputFormat.setOutputPath(job, new Path(args[1]));
			
			job.setJarByClass(AirlineDataDriver.class);
			job.submit();
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
