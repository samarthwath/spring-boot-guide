# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this is

A minimal Spring Boot REST API for blog posts (`com.embarkx.blogapi`), used as a teaching/exercise project (part of the EmbarkX Java/Spring Boot course material). It is a standalone Maven module living under `blog-api-project-starter-agents` inside the larger `spring-boot-guide` repo — treat this directory as the project root for build/test purposes.

Java 25, Spring Boot 4.0.5 (via `spring-boot-starter-parent`).

The included `BlogAPI.postman_collection.json` doubles as a spec of intentional bugs: several requests are explicitly named `(Bug: ...)` and describe defects that exist in `BlogController` on purpose (see Architecture below). Do not "fix" these unless asked — they are exercise fixtures, not accidental regressions.

## Commands

Run all commands from this directory (`blog-api-project-starter-agents`).

```
./mvnw compile                       # compile
./mvnw test                          # run all tests
./mvnw test -Dtest=BlogApiApplicationTests            # run a single test class
./mvnw test -Dtest=BlogApiApplicationTests#contextLoads   # run a single test method
./mvnw spring-boot:run               # run the app locally (port 8080)
./mvnw package                       # build the jar
```

On Windows use `mvnw.cmd` instead of `./mvnw`.

The `mvnw`/`mvnw.cmd` wrapper scripts are not currently committed in this module (only `.mvn/wrapper/maven-wrapper.properties` is present), so the `./mvnw`/`mvnw.cmd` commands above will fail with "file not found" until they're added. Use system `mvn` in their place (e.g. `mvn test`) until then.

There is no linter/formatter configured in this module.

## Architecture

- `BlogApiApplication` — standard `@SpringBootApplication` entry point, no custom configuration.
- `BlogController` (`/api/posts`) — the entire application logic lives in this one `@RestController`. There is no service layer, no repository/persistence layer, and no `Post` domain model: posts are stored as `"title:content"` strings in an in-memory `private final List<String> posts` (`CopyOnWriteArrayList`). `id` in path variables is just the current list index, not a stable identifier.
  - `POST /api/posts` (`title`, `content` request params) — create a post
  - `GET /api/posts` — list all posts
  - `GET /api/posts/{id}` — get a post by list index
  - `POST /api/posts/validate` (`content` request param) — validate content length
  - `DELETE /api/posts/{id}` — delete a post by list index
  - `GET /api/posts/total` — word count endpoint (see bug below)
- No database, no Spring Data, no security — this is intentionally bare-bones.
- `.claude/agents/` holds two subagent definitions used when working in this repo via Claude Code: `pr-reviewer` (reviews a PR/branch against a base branch, can post reviews back via the GitHub MCP tools) and `code-reviewer` (reviews a single file/class, not tied to git state).

### Known intentional bugs (see `BlogAPI.postman_collection.json`)

These exist to be discovered/fixed as part of the exercise, so don't be surprised by them and don't silently "clean them up" outside of an explicit task to do so:
- No validation on `createPost` — empty titles are accepted.
- `getPost`/`deletePost` use raw list indexing (`posts.get(id)` / `posts.remove(id)`) with no bounds checking — out-of-range `id` throws an unhandled exception instead of a proper 404.
- `validateContent` has a hardcoded max length (5000) rather than a configurable/validated constraint.
- `getWordCount` builds the total via naive string concatenation of word-count strings (`"100" + "200" + "300"` → `"100200300"`) instead of summing integers — the endpoint name promises a count but returns a concatenated string.