import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { getAllAthletes } from "./AthleteService";

export const useAthletes = () => {
  return useQuery({
    queryKey: ["athletes"],
    queryFn: getAllAthletes,
  });
};

// export const useAddAthleteToSession = () => {
//   const queryClient = useQueryClient();

//   return useMutation({
//     mutationFn: addAthleteToSession,
//     onSuccess: () => {
//       queryClient.invalidateQueries({ queryKey: ["athletes"] });
//     },
//   });
// };
