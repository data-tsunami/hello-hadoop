package ar.com.datatsunami.hellohadoop;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Test;

import ar.com.datatsunami.hellohadoop.MyFirstMapper;

public class SimpleMapperTest {

	public static final String val1 = "This is a line";
	public static final String val2 = "This is another line";

	@Test
	public void processLine() throws IOException {
		MapDriver<LongWritable, Text, Text, IntWritable> mapDriver = new MapDriver<LongWritable, Text, Text, IntWritable>();
		mapDriver.withMapper(new MyFirstMapper());
		mapDriver.addInput(new LongWritable(0), new Text(val1));
		mapDriver.addInput(new LongWritable(1), new Text(""));
		mapDriver.addInput(new LongWritable(2), new Text(val2));
		mapDriver.addInput(new LongWritable(3), new Text(val2));
		mapDriver.withOutput(new Text(Integer.toString(val1.length())),
				new IntWritable(1));
		mapDriver.withOutput(new Text("0"), new IntWritable(1));
		mapDriver.withOutput(new Text(Integer.toString(val2.length())),
				new IntWritable(1));
		mapDriver.withOutput(new Text(Integer.toString(val2.length())),
				new IntWritable(1));
		mapDriver.runTest();
	}

}
