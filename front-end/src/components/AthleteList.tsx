import type { Athlete } from "../services/AthleteService";
import AthleteCard from "./athleteCard/Athletecard";

interface AthleteListProps {
  athletes: Athlete[];
}

const AthleteList = ({ athletes }: AthleteListProps) => {
  if (athletes.length === 0) {
    return null;
  }

  return (
    <>
      {athletes.map((athlete) => (
        <AthleteCard key={athlete.id} athlete={athlete} />
      ))}
    </>
  );
};

export default AthleteList;
