package com.ut.oop.processor;

import com.ut.oop.MessageData;
import com.ut.oop.Socket;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ThresholdDataProcessor implements DataProcessor {

  /**
   * Processes device requests for controlling thresholds set by user for e.g temperature.
   * @param socket
   * @param message instance of MessageData class
   */
  @Override
  public void process(Socket socket, MessageData message) {

    String messageAsString = message.toString();
    ByteBuffer buffer = ByteBuffer.allocate(128);

    String[] data = messageAsString.split(System.lineSeparator()); // ei ole veel kindel lineseparatori osas :(


    double sum = 0;
    double i = 0;
    for (String number : data) {
      sum += Double.parseDouble(number);
      i += 1;
    }

    double average = sum/i;

    String command;
    if (average >= 0.7) {
      command = "lower levels";
    } else {
      command = "all is cool and good";
    }

    buffer.put(command.getBytes(StandardCharsets.UTF_8));
    socket.write(buffer);

  }
}
