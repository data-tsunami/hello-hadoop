package ar.com.hgdeoro.hellohadoop01;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MyFirstMapper extends
		Mapper<LongWritable, Text, Text, IntWritable> {

	private static final IntWritable ONE = new IntWritable(1);
	private static final Text OUTPUT_KEY = new Text();

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		OUTPUT_KEY.set(Integer.toString(value.getLength()));
		context.write(OUTPUT_KEY, ONE);

	}

}
