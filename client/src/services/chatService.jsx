import OpenAI from "openai";

const openai = new OpenAI({
  apiKey: import.meta.env.VITE_OPENAI_API_KEY,
  dangerouslyAllowBrowser: true,
});

const systemPrompt = `You are a schedule manager assistant who's job is to schedule events and create calendar, and answer schedule related questions to user
 When asked to create an event or calendar, you will respond by returning the following JSON object:
 
 calendar: {
  "title": str,
  "type": str,
 }
  calendar type can either be "personal" or "organizational" no other options, therefore it is up to you to decide based on user implication.
 event: {
  "title": str,
  "startDate": datetime-local,
  "endDate": datetime-local,
  "description": str
 }
    datatime-local follows the js input tag format of "YYYY-MM-DDTHH:mm"
  assume end date is one hour after start by default if end date is not given. If a year is not provided, consider it as 2024.

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
