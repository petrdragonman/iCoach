import axios from "axios";
import type { Athlete } from "./AthleteService";
import { BASE_URL } from "./serviceUtils";

//const BASE_URL = "http://localhost:8080";
const axiosInstance = axios.create({ baseURL: BASE_URL });

export interface Session {
  id?: number;
  sessionType: SessionType;
  craft: Craft;
  location: string;
  date: string;
  presentAthletes?: Array<Athlete>;
  presentAthleteIds: Array<number>;
}

type SessionType = "TRAINING" | "RACING" | "LAND" | "OTHER";
type Craft = "DB20" | "DB10" | "OC1" | "OC6" | "OTHER";

export const getAllSessions = async () => {
  const response = await axiosInstance.get<Session[]>("sessions");
  //console.log(response);
  return response.data;
};

export const getSessionById = async (id: number) => {
  const response = await axiosInstance.get<Session>(`sessions/${id}`);
  return response.data;
};

export const createSession = async (data: Session) => {
  const response = await axiosInstance.post("sessions", data);
  return response.data;
};

export const updateSession = async (data: Session) => {
  console.log(data);
  const response = await axiosInstance.patch(`sessions/${data.id}`, data);
  return response.data;
};

export const deleteSession = async (data: Session) => {
  const response = await axiosInstance.delete<Session>(`sessions/${data.id}`);
  return response.data;
};
