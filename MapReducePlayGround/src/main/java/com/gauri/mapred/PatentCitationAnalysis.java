package com.gauri.mapred;


import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapred.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


public class PatentCitationAnalysis {

	public static class PatentCitationMapper extends
			Mapper<Text, Text, Text, Text> {
		@Override
		protected void map(Text key, Text value, Context context)
				throws IOException, InterruptedException {
			context.write(key, value);
		}
	}

	public static class PatentCitationReducer extends
			Reducer<Text, Text, Text, Text> {
		
		protected void reduce(Text key, Iterable<Text> values, Context context)
				 {

			String csv = "";

			for (Text value : values) {
				if (csv.length() > 0) {
					csv += ",";
					csv += value.toString();
				}

			}
			try {
				context.write(key, new Text(csv));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		try {	
		Configuration conf = new Configuration();
		Job job;
	
			job = new Job(conf,"Gauri Patent Citation Analaysis");
		
		
		job.setJarByClass(PatentCitationAnalysis.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		
		job.setMapperClass(PatentCitationMapper.class);
		job.setCombinerClass(PatentCitationReducer.class);
		job.setReducerClass(PatentCitationReducer.class);

		//job.setInputFormatClass(KeyValueTextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.waitForCompletion(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
