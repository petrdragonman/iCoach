import axios from "axios";
import { handleError } from "../helpers/ErrorHandler";
import type { UserProfileToken } from "../models/User";

const api = "http://localhost:5173/";

export const loginAPI = async (username: string, password: string) => {
  try {
    const data = await axios.post<UserProfileToken>(api + "login", {
      username: username,
      password: password,
    });
    return data;
  } catch (error) {
    console.log(error);
    handleError(error);
  }
};

export const registerAPI = async (
  email: string,
  username: string,
  password: string
) => {
  try {
    const data = await axios.post<UserProfileToken>(api + "register", {
      email: email,
      username: username,
      password: password,
    });
    return data;
  } catch (error) {
    console.log(error);
    handleError(error);
  }
};
