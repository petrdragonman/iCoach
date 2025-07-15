import type { Session } from "../../services/SessionsService";

export const capitaliseEachWord = (str: string) => {
  return str
    .split("")
    .map(
      (word) =>
        word.charAt(0).toLocaleUpperCase() +
        word.substring(1).toLocaleLowerCase()
    )
    .join("");
};

export const totalFemaleAthletes = (session: Session) => {
  return session.presentAthletes?.filter(
    (athlete) => athlete.gender === "FEMALE"
  ).length;
};

export const totalMaleAthltes = (session: Session) => {
  return session.presentAthletes?.filter((athlete) => athlete.gender === "MALE")
    .length;
};
