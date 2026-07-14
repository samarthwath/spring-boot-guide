# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this project is

A deliberately minimal, single-controller Spring Boot starter app used as a hands-on exercise for practicing
code review and debugging with Claude Code. It is not a production codebase — it has no service layer, no
repository layer, and no persistence (data lives in a static in-memory `List` that resets on restart).

`BlogAPI.postman_collection.json` at the repo root documents a set of requests, several of which are labeled
with the bug they're meant to demonstrate (e.g. "Bug: no validation", "Bug: crashes", "Bug: string concat").
When asked to review, fix, or explain behavior in `BlogController`, check this file first — it's the source of
truth for which behaviors are known/intentional issues versus new regressions.

## Commands

All commands use the Maven wrapper from the project root (`blog-api-project-starter-main`).

```
# Windows
.\mvnw.cmd spring-boot:run       # run the app (default port 8080)
.\mvnw.cmd clean package         # build
.\mvnw.cmd test                  # run all tests
.\mvnw.cmd test -Dtest=BlogApiApplicationTests#contextLoads   # run a single test method
```

There is only one test class (`BlogApiApplicationTests`), and it currently just verifies the Spring context loads.

## Architecture

- `BlogApiApplication` — standard `@SpringBootApplication` entry point, no custom configuration.
- `BlogController` (`src/main/java/com/embarkx/blogapi/BlogController.java`) — the entire application logic.
  All endpoints live under `/api/posts` and operate directly on a `static List<String>` where each post is
  encoded as a single `"title:content"` string (no separate id, timestamp, or model class). Because posts are
  addressed by their current list index, deletions shift subsequent ids.
- No database, no DTOs/entities, no service or repository layers — everything is in the controller by design.
- `pom.xml` targets Java 25 on Spring Boot's `spring-boot-starter-webmvc` starter (the Spring Boot 4 module
  name; not `spring-boot-starter-web`). Lombok is wired into the compiler's annotation-processor path and
  excluded from the repackaged jar, but isn't declared as an explicit dependency — don't assume Lombok
  annotations will compile without adding it first.

## Repo context

This directory is a subproject inside a larger personal monorepo (`spring-boot-guide`); the git root is one
level up. Treat `blog-api-project-starter-main` as self-contained — commit history above this directory covers
unrelated exercises (interview prep, other Spring/Java projects) and isn't relevant here.
