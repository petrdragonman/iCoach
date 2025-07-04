import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import {
  createSession,
  deleteSession,
  getAllSessions,
  getSessionById,
  updateSession,
} from "./SessionsService";

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

/**
 * import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import {
  createSession,
  deleteSession,
  getAllSessions,
  getSessionById,
  updateSession,
  type Session,
} from "./SessionsService";

export function useSessions(id: number | undefined) {
  return useQuery({
    queryKey: id ? ["sessions", id] : ["sessions"],
    queryFn: () => {
      if (id === 0 || id === null) return getAllSessions();
      if (id! == null) {
        getSessionById(id!);
      }
    },
  });
}

export const useCreateSesssion = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: (data: Session) => createSession(data),
    onMutate: () => {
      console.log("on mutate");
    },
    onError: () => {
      console.log("on error");
    },
    onSuccess: () => {
      console.log("on success");
    },
    onSettled: async (data, error) => {
      console.log("settled", data);
      if (error) {
        console.log(error.message);
      } else {
        await queryClient.invalidateQueries({ queryKey: ["sessions"] });
      }
    },
  });
};

export const useUpdateSession = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: (data: Session) => updateSession(data),
    onSettled: async (_, error, variables) => {
      if (error) {
        console.log(error.message);
      } else {
        await Promise.all([
          queryClient.invalidateQueries({ queryKey: ["sessions"] }),
          queryClient.invalidateQueries({
            queryKey: ["sessions", { id: variables.id }],
          }),
        ]);
        // await queryClient.invalidateQueries({
        //   queryKey: ["sessions", { id: variables.id }],
        // });
      }
    },
  });
};

export const useDeleteSession = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: (data: Session) => deleteSession(data),
    onSettled: async (_, error, variables) => {
      if (error) {
        console.log(error.message);
      } else {
        console.log(variables.id);
        await queryClient.invalidateQueries({
          queryKey: ["sessions"],
        });
      }
    },
  });
};

 */
