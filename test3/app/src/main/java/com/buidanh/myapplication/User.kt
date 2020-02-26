package com.buidanh.myapplication

/**
 * Representation of a user
 *
 * @author Guilherme Biff Zarelli
 */
class User(val userId: Int, val name: String) {
    override fun toString() = "User(userId=$userId, name='$name')"
}