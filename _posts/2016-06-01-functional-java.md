---
layout: post
title:  "Writing Functional Java"
date:   2016-06-01 18:08:14 -0500
categories: Java
---

## Intro
If you're a Java developer (and haven't been "living under a rock"), you've probably _at least_ heard of the new language features introduced in Java 8.
Now that Java has lambdas and method references available to it, it opens up a whole new set of possibilities when it comes to writing code.
One example of this is the new [Streaming API](http://www.oracle.com/technetwork/articles/java/ma14-java-se-8-streams-2177646.html) which has been added
to the Java collections framework in version 8.

My goal with this post is to demonstrate a number of ways that you can effectively bring functional concepts into your Java code.

## Why?
A quick detour: _why would you want to do this?_ Java is an object-oriented language, so why bother bringing functional concepts into your code?

* Firstly, I'm not suggesting that anyone try to write Java code which is "purely functional" (if that's even possible). Certainly many OO and procedural
design patterns are still valid. I believe functional concepts can be applied where they make sense, leaving code which benefits from a hybrid of
different methodologies.
* Secondly, functional code - when used in the right way - is less prone to errors than its procedural equivallent, while at the same time being more expressive.
Functional expressions tend to "flow" from one end to the other, making it easier to see what's being done.
* Lastly, much of the Java code that's written focuses on finding, collecting, transforming, sorting, and transporting of data.
These types of operations are exactly the types of expressions that are best described in a functional way.
This means we have lots of opportunities to apply these functional concepts.

## Examples
Enough talk. Let's see some examples of what I'm talking about...

# Scenario 1: The Basics

