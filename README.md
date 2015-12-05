# Canal Simulator

## Introduction

This project is to implement a canal simulator which using concurrency
programming that allowing multiple threads, which operate the lock and every
sections for coming vessels, to run at the same time and be efficient to operate
each thread depending on the priority and logic of operation of each thread.
This project basically implement the concept of monitor that allowing lock the
object when one of threads process this object so that other threads have to
wait in order to process same object.

This project is built for SWEN40004 Assignment 2 in UniMelb 2014-SM1.

The system to be simulated is a canal/lock system. The canal is somehow kept at
the appropriate water level by a mechanism that we don’t worry about. It only
allows one-way traffic and is divided into a number of sections(six in the
example). A canal section can not be occupied by more than one vessel at a time.
A vessel arrives from downstream at the lock and, provided the lock is
unoccupied and the chamber is drained, the vessel can enter and be raised,
allowing it to then travel through the sections 0, 1, 2,... Eventually it will
arrive back at the lock and, provided the lock is unoccupied and the chamber is
filled, the vessel can enter the lock and be lowered, allowing it to depart the
system. This simulator is designed for resolve the concurrency issue in this
system.

Vessels arrive from downstream, at random times, under their own steam. However,
they need to be towed from the lock into the first section, from one section to
the next, and from the last section back into the lock. For this, three types of
tugboats are used. A special “launch” tugboat transfers vessels from the lock to
the first canal section. A special “return” tugboat transfers vessels from the
last section back to the lock. A number of ordinary tugboats tow vessels from
one section to the successor section. Each ordinary tugboat is committed to
serving the same pair of sections continually, picking up from section k and
delivering to section k + 1.

Hence it is sometimes necessary to fill or drain the chamber even if no vessel
is in the lock. It is the task of an “operator” to occasionally fill or drain
the lock chamber like that.

Assume that there is no other vessels in the system, the related operations for
a vessel in this system can be described as:

![alt
tag](https://cloud.githubusercontent.com/assets/2654264/11605981/c69928c8-9b63-11e5-871a-93b8528f713a.png)

## How to run

* Option1 open eclipse and nevigate to **Main.java**, then click "run" button in eclipse.
* Option2 using makefile in package __src__ by type in "make" on terminal, and run the
program by command **java Main**

## Documentation
Documentation of this project can be found in __doc__ folder.

## Author 

Litao Shen (litaoshen_0315@hotmail.com)

## Contributions
