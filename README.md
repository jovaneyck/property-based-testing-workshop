# property-based-testing-workshop
Starting code for the "introduction to property based testing" workshop.
Feel free to adapt the slideware/exercises to suit your own workshop needs!

Accompanying slides can be found [here](https://github.com/jovaneyck/property-based-testing-workshop/blob/main/hands-on-property-based-testing.pdf).

## Why properties?

We all know unit tests. As these are great at catching regressions, they can only catch the cases you thought about while writing the tests.

On the other end of the verification spectrum we have formal proofs. These can prove certain things about your code, but are typically very expensive to write.

Properties fall somewhere in between. They're a bit more "heavyweight" than classical unit tests but can catch edge cases you didn't think off, without needing a phd in mathematics to do formal proofs.

## What are properties?

A property states something about your code that is always true. These are all properties for a reverse() function that reverses lists:

* Every element in the original list is contained in the reversed list
* Reversing a reversed list results in the original list
* The reversed list has the same number of elements as the original list

The way all these test libraries work is as follows:

1. You tell the library what kind of input data you need for a property (for example: "any non-empty list of integers")
2. You write the property as a plain function that accepts the kind of input you specified above and returns true or false
3. When you run your tests (or properties), the library goes to work. It generates *random* data according to your specifications in 1) and evaluates the property you defined in 2). In case of a failure, it prints out the specific input so you can get to debugging. 

That's it! Magic! 🦄 In practice there are a lot more subtleties and features, but this should be enough to start the workshop.

## Heuristics for good properties

Writing good properties is something of an art, but this is a good list of heuristics to help you get started:

### You can throw anything at it

Properties of this category validate that whatever inputs you provide, your code should not crash.

### Different paths, same destination

If your API has multiple functions, sometimes the order in which you apply the functions does not matter. For example, increasing every number in a list by 1 and then sorting it should result in the same list as first sorting it and only then increasing every number by 1.

### There and back again

Sometimes your API has functions that are the *reverse* of each other. Serializing a record to JSON and deserializing it should result in the original record.

### Some things never change

Some aspects about your data *don't* change. For example, when sorting a list, the number of elements in the list should stay the same.

### The more things change, the more they stay the same

Some operations are *idempotent*. Sorting a list once, twice or three times should not change the result. 

## Workshop outline

You are given some working code and a test harness that supports writing property based tests.
Your job is to write new properties for the existing code using the following heursitics:

## kata description
You can find the original mars rover kata description [here](https://kata-log.rocks/mars-rover-kata).

## Workspaces

This repository contains projects you can run in your favorite IDE. If you don't have an IDE at the ready, you can also try this exercise in an online repl.it. Click on your preferred language and **"Fork Repl"** 

* [C# repl.it](https://replit.com/@praGmatic/pbt-csharp?v=1)
* [F# repl.it](https://replit.com/@praGmatic/pbt-fsharp?v=1)
* [typescript codepen.io](https://codepen.io/jovaneyck/pen/YzvRggq?editors=0010)

## Resources

* [An introduction to property based testing](https://fsharpforfunandprofit.com/pbt/), Scott Wlaschin
* [How to specify it! in Java!](https://johanneslink.net/how-to-specify-it/), Johannes Link
