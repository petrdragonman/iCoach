import * as z from "zod";

export const sessionType = ["TRAINING", "RACING", "LAND", "OTHER"] as const;
export const craft = ["DB20", "DB10", "OC1", "OC6", "OTHER"] as const;

export const schema = z.object({
  sessionType: z.enum(sessionType, { message: "Invalid session type input" }),
  craft: z.enum(craft, { message: "Invalid craft type input" }),
  location: z.string().min(1, { message: "location is required" }),
  date: z
    .string()
    .regex(/^\d{4}-\d{2}-\d{2}$/, { message: "invalid date: yyyy-mm-dd" }),
});

export type SessionFormData = z.infer<typeof schema>;
