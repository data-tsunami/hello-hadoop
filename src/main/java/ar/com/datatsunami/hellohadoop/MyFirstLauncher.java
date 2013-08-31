package ar.com.datatsunami.hellohadoop;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class MyFirstLauncher extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {

		Job job = new Job(getConf(), "My first MR job");
		job.setJarByClass(getClass());

		FileInputFormat.addInputPath((JobConf) job.getConfiguration(),
				new Path(args[0]));
		FileOutputFormat.setOutputPath((JobConf) job.getConfiguration(),
				new Path(args[1]));

		job.setMapperClass(MyFirstMapper.class);
		job.setReducerClass(MyFirstReducer.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		return job.waitForCompletion(true) ? 0 : 1;
	}

	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new MyFirstLauncher(), args);
		System.exit(exitCode);
	}
}
