package com.gauri.maxtemperature;

import java.io.IOException;

import javafx.scene.control.TextFormatter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class MaxTemperatureDriver {

	public static void main(String[] args) {
		try {
			 if (args.length != 2) {
		          System.out.println("usage: [input] [output]");
		          System.exit(-1);
		        }
			
			Job job = new Job(new Configuration(), "Max Temp");
			
			job.setInputFormatClass(TextInputFormat.class);
			job.setOutputFormatClass(TextOutputFormat.class);
			
			job.setMapperClass(MaxTempMapper.class);
			job.setReducerClass(MaxTempReducer.class);
			
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(IntWritable.class);
			
			FileInputFormat.setInputPaths(job, new Path(args[0]));
			FileOutputFormat.setOutputPath(job, new Path(args[1]));
			
			job.setJarByClass(MaxTemperatureDriver.class);
			job.submit();
			
			
			
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
