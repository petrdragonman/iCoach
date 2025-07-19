import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import {
  addAthleteToSession,
  createSession,
  deleteSession,
  getAllSessions,
  getSessionById,
  removeAthleteFromSession,
  updateSession,
} from "./SessionsService";

interface SessionMutationParams {
  sessionId: number;
  athleteId: number;
}

export function useSessions() {
  return useQuery({
    queryKey: ["sessions"],
    queryFn: getAllSessions,
  });
}

export function useSession(id: number) {
  return useQuery({
    queryKey: ["sessions", id],
    queryFn: () => getSessionById(id),
  });
}

export const useCreateSession = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: createSession,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["sessions"] });
    },
  });
};

export const useAddAthleteToSession = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({ sessionId, athleteId }: SessionMutationParams) =>
      addAthleteToSession(sessionId, athleteId),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["sessions"] });
    },
  });
};

export const useRemoveAthleteFromSession = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({ sessionId, athleteId }: SessionMutationParams) =>
      removeAthleteFromSession(sessionId, athleteId),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["sessions"] });
    },
  });
};

export const useUpdateSession = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: updateSession,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["sessions"] });
    },
  });
};

export const useDeleteSession = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: deleteSession,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["sessions"] });
    },
  });
};
