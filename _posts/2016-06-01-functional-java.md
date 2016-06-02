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

# Scenario 1: Streaming Basics
One of the most common patterns I've encountered in my years of writing code - no matter the language - is that of taking a collection of one thing,
and transforming it into a collection of something else. Here's a traditional example of something like this in Java:

{% highlight java %}
/**
 * Converts a list of numbers to a list of strings.
 */
public List<String> convertNumbersToStrings(List<Integer> numbers) {
    List<String> strings = new ArrayList<>(numbers.size());
    for (Integer number : numbers) {
        strings.add(number.toString());
    }
    return strings;
}
{% endhighlight %}

"What's wrong with this approach?" you might ask. Well, it's not so much that it's _wrong_, only that it could be _better_. The code itself isn't very
expressive. We spend extra characters dealing with _how_ we're doing it, and that tends to obfuscate _what_ we're trying to do.

* The first line is dealing entirely with creating a new container for the result of the operation which looks just like the input container, but
holds a different type.
* The second line verbosely expresses that we want to do something to each item in the input container.
* The third line finally deals with the conversion from Integer to String, but it's also muddied by adding the result of that conversion to our result
container
* The last line separatey expresses that we want to return the result container.

Obviously, this is a pretty simple example (and one which I have perhaps overly picked apart), but if it were more complex, it would get much harder
to "read" what was going on. What if we expressed this in a functional way (using streaming operations)?

{% highlight java %}
/**
 * Converts a list of numbers to a list of strings.
 */
public List<String> convertNumbersToStringsFunctionally(List<Integer> numbers) {
    return numbers.stream()
            .map(Object::toString)
            .collect(Collectors.toList());
}
{% endhighlight %}

As you can see, the code reads much more of _what_, and a lot less of _how_. The entire operation is expressed as a single functional statement ad it
"flows" from one step to the next, making it much more readable.
