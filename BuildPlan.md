The repo is a multi-module Eclipse-era project: astrid (app), api (plugin API), plus libraries like ActionBarSherlock, GreenDroid, ViewPagerIndicator, Facebook SDK, and an AAC encoder; it targeted Android 4.x back then and built with Ant/Eclipse.  ￼ ￼

Target architecture
	•	Kotlin + Gradle (AGP 8.x)
	•	AndroidX everything
	•	Jetpack: Room (storage), WorkManager (sync/alarms), ViewModel + LiveData/Flow, Navigation, Hilt (DI)
	•	Networking: Retrofit + OkHttp + Moshi (or Kotlinx Serialization)
	•	UI: you can keep Views initially; migrate to Jetpack Compose incrementally

Concrete first pass (day 1 checklist)
	1.	Create a fresh Gradle wrapper & settings
	•	In Cursor: initialize a new empty Android app (Package name com.todoroo.astrid to preserve DB & prefs), AGP 8.x, minSdk 23 or 24, target/compile latest.
	•	Copy the legacy Java packages from astrid/src into app/src/main/java, but exclude obsolete libs.
	2.	Replace legacy libs
	•	ActionBarSherlock → AppCompat / Material
	•	ViewPagerIndicator → ViewPager2 + TabLayoutMediator
	•	GreenDroid widgets → native RecyclerView + Material components
	•	Apache HttpClient → OkHttp (through Retrofit)
	3.	AndroidX & manifests
	•	Run AndroidX migration; update manifest permissions and services.
	•	Convert old AlarmManager repeating tasks to WorkManager periodic work.
	4.	Database
	•	Identify existing SQLite helper(s); wrap with Room entities/DAO. Keep the same table names/columns first to avoid a data migration cliff.
	5.	Sync entry points
	•	Locate sync code (you’ll see classes that referenced astrid.com or “Api*”, “Sync*”, “TodorooHttp”, etc.). Create a thin Retrofit interface that matches those endpoints (see backend section below), then swap callers behind an interface.
	6.	Kotlin-ize hot paths
	•	Convert network & repository layers first (Cursor’s refactor is great for this), then screens as you touch them. Compose migration can wait.


    After this I want to build a backend API that is compatible with the old API but uses a modern backend using:

    	•	Backend: TypeScript NestJS (or Kotlin Spring Boot/Ktor if you prefer JVM)
	•	Auth: JWT for new paths; legacy apikey/signature middleware for compatibility
	•	DB: Postgres + Prisma (or JPA/Hibernate)
	•	Migrations: Prisma Migrate/Flyway
	•	Deploy: Vercel serverless (edge not required) or a simple container on Fly.io/Render; Neon or RDS for Postgres