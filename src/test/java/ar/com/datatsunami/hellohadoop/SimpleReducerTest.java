package ar.com.datatsunami.hellohadoop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Test;

import ar.com.datatsunami.hellohadoop.MyFirstReducer;

public class SimpleReducerTest {

	@Test
	public void processLine() throws IOException {
		ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver = new ReduceDriver<Text, IntWritable, Text, IntWritable>();
		reduceDriver.withReducer(new MyFirstReducer());

		List<IntWritable> list = new ArrayList<IntWritable>();
		list.add(new IntWritable(1));
		list.add(new IntWritable(1));
		list.add(new IntWritable(1));
		reduceDriver.addInput(new Text("71"), list);

		list = new ArrayList<IntWritable>();
		list.add(new IntWritable(1));
		reduceDriver.addInput(new Text("9"), list);

		reduceDriver.withOutput(new Text("71"), new IntWritable(3));
		reduceDriver.withOutput(new Text("9"), new IntWritable(1));

		reduceDriver.runTest();
	}
}
