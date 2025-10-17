package com.rupp.news_app_demo_android.extra_demo

import kotlinx.coroutines.*
import kotlin.random.Random
import kotlin.system.measureTimeMillis

// --- SECTION 1: COROUTINE FUNDAMENTALS ---
/**
 * This section demonstrates the basic concept of coroutines vs. traditional blocking calls.
 * We simulate a real-world scenario of loading user data from multiple sources.
 */
suspend fun loadDataSequentially() {
    println("--- Starting sequential data load (blocking) ---")
    val time = measureTimeMillis {
        val userProfile = fetchUserProfile()
        val userPosts = fetchUserPosts()
        val userFriends = fetchUserFriends()

        println("Sequential load complete. Profile: $userProfile, Posts: $userPosts, Friends: $userFriends")
    }
    println("Sequential load took $time ms.\n")
}

suspend fun loadDataConcurrently() = coroutineScope {
    println("--- Starting concurrent data load (with coroutines) ---")
    val time = measureTimeMillis {
        val profileDeferred = async { fetchUserProfile() }
        val postsDeferred = async { fetchUserPosts() }
        val friendsDeferred = async { fetchUserFriends() }

        val userProfile = profileDeferred.await()
        val userPosts = postsDeferred.await()
        val userFriends = friendsDeferred.await()

        println("Concurrent load complete. Profile: $userProfile, Posts: $userPosts, Friends: $userFriends")
    }
    println("Concurrent load took $time ms.\n")
}

// --- SECTION 2: SUSPEND FUNCTIONS AND SCOPES ---
/**
 * A suspend function is a key building block of coroutines.
 * It's a function that can be paused and resumed at a later time.
 * This section defines suspend functions that simulate network calls.
 */
suspend fun fetchUserProfile(): String {
    println("Fetching user profile...")
    delay(Random.nextLong(9000, 10000)) // Simulate network delay
    return "John Doe Profile"
}

suspend fun fetchUserPosts(): String {
    println("Fetching user posts...")
    delay(Random.nextLong(1000, 1500)) // Simulate network delay
    return "10 recent posts"
}

suspend fun fetchUserFriends(): String {
    println("Fetching user friends...")
    delay(Random.nextLong(1500, 2000)) // Simulate network delay
    return "150 friends"
}

// --- SECTION 3: DISPATCHERS DEEP DIVE ---
/**
 * Dispatchers control the thread on which a coroutine runs.
 * We'll use them to simulate a real-world UI scenario where a heavy
 * operation would block the main thread without proper dispatching.
 */


fun main() = runBlocking {
    println("--------------------------------------------------")
    println("DEMO 1: COROUTINE FUNDAMENTALS (Sequential vs. Concurrent)")
    println("--------------------------------------------------")

    // Demonstrate sequential blocking behavior
//    loadDataSequentially()
//
////    // Demonstrate concurrent non-blocking behavior
//    loadDataConcurrently()

    println("\n\n--------------------------------------------------")
    println("DEMO 2: SUSPEND FUNCTIONS AND SCOPES")
    println("--------------------------------------------------")

    // This part demonstrates a coroutine scope that ensures both
    // child tasks (fetchUserProfile and fetchUserPosts) complete
    // before the overall block finishes.
//    coroutineScope {
//        println("Starting a new CoroutineScope...")
//        val profile = async { fetchUserProfile() } // 10s
//        val posts = async { fetchUserPosts() } // 2s
//
//        println("Waiting for both profile and posts to be fetched...")
//        val result = "Profile: ${profile.await()}, Posts: ${posts.await()}"
//        println("CoroutineScope is complete. Result: $result\n")
//    }
//
    println("\n\n--------------------------------------------------")
    println("DEMO 3: DISPATCHERS DEEP DIVE")
    println("--------------------------------------------------")

    println("Simulating an Android app's UI thread. All heavy work will be done on background threads.")

    // Simulate a button click on the UI/Main thread
    println("\nUser clicks 'Load Profile' button...")

    launch(Dispatchers.Unconfined) { // In a real Android app, this would be on the UI thread
        println("UI: Showing loading indicator...")

        // Run heavy I/O operations on the IO dispatcher
        val profileData = withContext(Dispatchers.IO) {
            println("IO Dispatcher: Starting network call for profile data...")
            // The suspending function handles the pause/resume
            fetchUserProfile()
        }

        val postsData = withContext(Dispatchers.IO) {
            println("IO Dispatcher: Starting database query for posts...")
            // Simulating another heavy I/O operation
            fetchUserPosts()
        }

        // Run CPU-intensive work on the Default dispatcher
        val processedData = withContext(Dispatchers.Default) {
            println("Default Dispatcher: Processing the retrieved data...")
            // Simulate a heavy computational task
            delay(500)
            "Processed: $profileData & $postsData"
        }

        // Switch back to the Main dispatcher to update the UI
        withContext(Dispatchers.Unconfined) {
            println("UI: Hiding loading indicator.")
            println("UI: Displaying processed data: '$processedData'")
        }
    }

    println("Main thread is free to do other work while coroutines run in the background.")
    delay(5000) // Keep main thread alive to see all logs
}
