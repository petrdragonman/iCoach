import { useState } from "react";
import type { Athlete } from "../../services/AthleteService";
import {
  useAddAthleteToSession,
  useRemoveAthleteFromSession,
} from "../../services/queries";

interface AthleteCardProps {
  athlete: Athlete;
  sessionId: number;
}

const AthleteCard = ({ athlete, sessionId }: AthleteCardProps) => {
  const isInTheList = athlete.attendedSessionIds?.includes(sessionId);
  const [present, setPresent] = useState(isInTheList);

  const removeAthleteFromSessionMutation = useRemoveAthleteFromSession();
  const addAthleteToSessionMutation = useAddAthleteToSession();

  //console.log(athlete);

  const handleEdit = () => {};

  const toggleIsPresent = () => {
    // Early return if athlete.id is undefined
    if (athlete.id === undefined) {
      console.error("Athlete ID is undefined");
      return;
    }

    const athleteId = athlete.id;

    // Optimistic UI update - update local state immediately
    setPresent(!present);

    try {
      if (present) {
        console.log("Removing athlete from session");
        removeAthleteFromSessionMutation.mutate(
          { sessionId, athleteId },
          {
            onError: () => {
              // Revert if the mutation fails
              setPresent(present);
              console.error("Failed to remove athlete");
            },
          }
        );
      } else {
        console.log("Adding athlete to session");
        addAthleteToSessionMutation.mutate(
          { sessionId, athleteId },
          {
            onError: () => {
              // Revert if the mutation fails
              setPresent(present);
              console.error("Failed to add athlete");
            },
          }
        );
      }
    } catch (error) {
      console.error("Error in toggleIsPresent:", error);
      setPresent(present); // Revert on unexpected errors
    }
  };

  return (
    <>
      <div className="flex justify-between gap-2 p-2 my-1 shadow-md shadow-orange-200/30 border border-gray-200 rounded min-w-lg">
        <article className="flex gap-4">
          <section className="flex justify-center items-center gap-2 text-4xl">
            {athlete.gender === "FEMALE" ? <p>🙍‍♀️</p> : <p>🙎‍♂️</p>}
          </section>
          <article onClick={handleEdit} className="flex gap-2">
            <section className="flex flex-col">
              <article className="flex gap-2">
                <p className="text-2xl text-orange-500">{athlete.nickName}</p>
              </article>
              <p className="italic text-gray-600">
                {athlete.firstName} {athlete.lastName}
              </p>
            </section>
          </article>
        </article>

        <article
          onClick={toggleIsPresent}
          className="flex justify-center items-center gap-2 pl-10 text-2xl"
        >
          {present ? <p>✅</p> : <p>☑️</p>}
        </article>
      </div>
    </>
  );
};

export default AthleteCard;
