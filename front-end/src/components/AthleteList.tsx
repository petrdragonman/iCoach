import type { Athlete } from "../services/AthleteService";
import AthleteCard from "./athleteCard/AthleteCard";

interface AthleteListProps {
  athletes: Athlete[];
  sessionId: number;
}

const AthleteList = ({ athletes, sessionId }: AthleteListProps) => {
  if (athletes.length === 0) {
    return null;
  }

  return (
    <>
      {athletes.map((athlete) => (
        <AthleteCard key={athlete.id} athlete={athlete} sessionId={sessionId} />
      ))}
    </>
  );
};

export default AthleteList;
