export interface Session {
  id: number;
  sessionName: string;
  date: string;
}

export const getAllSessions = async () => {
  const response = await fetch("http://localhost:8080/sessions");
  return await response.json();
};
