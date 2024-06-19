package com.vtw.dna.graal;

import org.graalvm.polyglot.*;

import java.util.List;
import java.util.Map;

class App {

     static String JS_CODE = "function test(aaa, bbb) { return aaa  + 'Js!'}";

     public static void main(String[] args) {
         try (var context = Context.newBuilder().option("engine.WarnInterpreterOnly", "false").build()) {

//             Value value = context.eval("python", "def data():" +
//                     "  return 'aaa'");
//
//             Source script = Source.create("python", "def data():" +
//                     "  return 'aaa'");
             Value value = context.eval("python", "def swapList(aaa):\n" +
                     "     return aaa");
             context.eval("python", "def hhh(aaa, bbb):\n" +
                     "     return aaa + 'Python'");
             System.out.println("Result: " + context.getBindings("python").getMember("hhh").execute("AAA", "EEE").asString());


             Value value2 = context.eval("js", JS_CODE);
//             System.out.println("Result: " + value.asString());
             System.out.println("Result: " + context.getBindings("js").getMember("test").execute("cccc", "aaaa").asString());
         }

//         try (Context context = Context.create()) {
//             Value value = context.eval("js", JS_CODE);
////             System.out.println("Result: " + value.asString());
//             System.out.println("Result: " + context.getBindings("js").getMember("test").execute("cccc", "aaaa").toString());
//
//         }
     }
 }
