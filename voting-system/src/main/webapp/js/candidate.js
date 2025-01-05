// Variable to track if a user has already voted
let hasVoted = false;

// Get all vote buttons
const voteButtons = document.querySelectorAll('.vote-button');

// Function to get the candidate name based on the button clicked
function getCandidateName(candidateId) {
    const candidateNames = {
        1: "BJP",
        2: "CONGRESS",
        3: "AAP",
        4: "AITC",
        5: "SHIV SENA",
        6: "JDU",
        7: "BSP",
        8: "RJD",
        9: "LJP"
    };
    return candidateNames[candidateId] || "Unknown Party";
}

// Add click event listener to each vote button
voteButtons.forEach(button => {
    button.addEventListener('click', function() {
        // Check if user has already voted
        if (hasVoted) {
            // Show a message if user tries to vote more than once
            alert("You can vote only once!");
            return; // Exit the function if user already voted
        }

        const candidateId = this.getAttribute('data-candidate');
        const candidateName = getCandidateName(candidateId);

        // Show a confirmation popup with the candidate name
        const confirmation = confirm(`You have voted for ${candidateName}. Do you want to confirm your vote?`);

        if (confirmation) {
            // Mark that the user has voted
            hasVoted = true;

            // Here you can implement the logic to actually store the vote, for example:
            // - Send an AJAX request to the server
            // - Update the vote count in the database
            // For now, we will just alert the success message
            alert(`Your vote for ${candidateName} has been successfully recorded.`);
        } else {
            // If user cancels the vote
            alert("You have canceled your vote.");
        }
    });
});
