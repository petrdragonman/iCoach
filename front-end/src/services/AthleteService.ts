import axios from "axios";
import { BASE_URL } from "./serviceUtils";
import type { Session } from "react-router";

const axiosInstance = axios.create({ baseURL: BASE_URL });

export interface Athlete {
  id?: number;
  firstName: string;
  lastName: string;
  nickName: string;
  gender: Gender;
  weight: number;
  attendedSessions?: Array<Session>;
  attendedSessionIds?: Array<number>;
}

type Gender = "MALE" | "FEMALE";

export const getAllAthletes = async () => {
  const response = await axiosInstance.get<Athlete[]>("athletes");
  //console.log(response);
  return response.data;
};

// http://localhost:8080/sessions/1?athleteId=3
// export const addAthleteToSession = async (
//   sessionId: number,
//   athleteId: number
// ) => {
//   const response = await axiosInstance.post(
//     `sessions/${sessionId}?athleteId=${athleteId}`
//   );
//   return response.data;
// };
