import { useQuery } from "@tanstack/react-query";
import { getAllAthletes } from "./AthleteService";

export const useAthletes = () => {
  return useQuery({
    queryKey: ["athletes"],
    queryFn: getAllAthletes,
  });
};
