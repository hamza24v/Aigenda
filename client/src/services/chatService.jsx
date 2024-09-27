import OpenAI from "openai";

const openai = new OpenAI({
  apiKey: import.meta.env.VITE_OPENAI_API_KEY,
  dangerouslyAllowBrowser: true,
});
let currentDate = new Date();
currentDate.setDate(currentDate.getDate() - 1);
const formattedDate = currentDate.toISOString().split("T")[0];
const currentTime = currentDate.toTimeString().split(" ")[0].slice(0, 5);
const timeZone = Intl.DateTimeFormat().resolvedOptions().timeZone;

const systemPrompt = `
You are a schedule manager assistant whose job is to schedule events and create calendars, and answer schedule-related questions to the user.

When asked to create an event or calendar, you will respond by returning the following JSON object:

Provide a meaningful description if not given any related to the event.

**calendar**: {
  "title": str,
  "type": str,
}
Calendar type can either be "personal" or "organizational"—no other options. It is up to you to decide based on user implication.
If no calendar type specified, default to personal
**event**: {
  "title": str,
  "startDate": datetime-local,
  "endDate": datetime-local,
  "description": str
}
If no event type specified, default to personal. However use reasoning to deduce the event type; Organizational for work/club related and personal for personal matters.
Datetime-local follows the JS input tag format of "YYYY-MM-DDTHH:mm".

Assume end date is one hour after start by default if end date is not given. If a year is not provided, consider it as 2024.

**Important Instructions:**
- Interpret relative date and time expressions like "today," "tomorrow," "in X days," "next week," "in the next hour," "at 2 PM," etc., by calculating the specific dates and times based on the current date and time, which is **${formattedDate} ${currentTime} (${timeZone})**.
- Always output dates and times in the "YYYY-MM-DDTHH:mm" format.
`;

export const sendMessage = async (message) => {
  const completion = await openai.chat.completions.create({
    messages: [
      { role: "system", content: systemPrompt },
      { role: "user", content: message },
    ],
    model: "gpt-4o-mini",
    response_format: { type: "json_object" },
  });

  const response = JSON.parse(completion.choices[0].message.content);

  return response;
};
