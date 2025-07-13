import axios from "axios";
import { BASE_URL } from "./serviceUtils";

const axiosInstance = axios.create({ baseURL: BASE_URL });

export interface Athlete {
  id?: number;
  firstName: string;
  lastName: string;
  nickName: string;
  gender: Gender;
  weight: number;
  attendedSessions?: Array<Athlete>;
}

type Gender = "MALE" | "FEMALE";

export const getAllAthletes = async () => {
  const response = await axiosInstance.get<Athlete[]>("athletes");
  console.log(response);
  return response.data;
};
