# GitHub Activity CLI 
(https://roadmap.sh/projects/github-user-activity)

A simple command line interface (CLI) application that fetches and displays the recent activity of a GitHub user directly in the terminal.

## Project Overview

I built this CLI tool to practice working with APIs, handling JSON data, and creating command line applications. The application fetches a user's recent GitHub activity using the GitHub API and presents it in a clean, readable format.

## Installation

1. Clone this repository:
```bash
git clone https://github.com/yourusername/github-activity-cli.git
cd github-activity-cli
```

2. Make the script executable (if using a script-based language):
```bash
chmod +x github-activity
```

## Usage

Run the application with a GitHub username as an argument:

```bash
github-activity <username>
```

### Example

```bash
github-activity kaiuuganbadrakh
```

### Sample Output

```
- Pushed 3 commits to kaiuuganbadrakh/developer-roadmap
- Opened a new issue in kaiuuganbadrakh/developer-roadmap
- Starred kaiuuganbadrakh/developer-roadmap
- Forked octocat/Hello-World
- Created a new branch in kaiuuganbadrakh/developer-roadmap
```

## API Endpoint

The application uses the GitHub Events API to fetch user activity:

```
https://api.github.com/users/<username>/events
```

## Error Handling

The application handles various error scenarios:

- Invalid or non-existent usernames
- Network connectivity issues
- API rate limiting
- Malformed API responses

## Implementation Details

I implemented this project with the following considerations:

- **No External Dependencies**: Built using only standard library functions for HTTP requests and JSON parsing
- **Clean Output**: Formatted activity display with clear, readable messages
- **Robust Error Handling**: Comprehensive error checking and user-friendly error messages
- **Efficient API Usage**: Respectful use of the GitHub API with proper error handling

## Contributing

Feel free to fork this project and submit pull requests for any improvements or bug fixes.

## License

This project is open source and available under the [MIT License](LICENSE).

## Resources

- [GitHub API Documentation](https://docs.github.com/en/rest)
- [GitHub Events API](https://docs.github.com/en/rest/activity/events)

---

*This project was created as part of my journey to improve my programming skills and learn more about API integration and CLI development.*
