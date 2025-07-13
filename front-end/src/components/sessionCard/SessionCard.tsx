import { useNavigate } from "react-router";
import { useDeleteSession } from "../../services/queries";
import type { Session } from "../../services/SessionsService";
import { format } from "date-fns";

interface SessionCardProps {
  session: Session;
}

const SessionCard = ({ session }: SessionCardProps) => {
  const deleteSessionMutation = useDeleteSession();
  const navigate = useNavigate();

  console.log(session);

  const handleDelete = () => {
    deleteSessionMutation.mutate(session);
  };

  const handleEdit = () => {
    console.log("hadle edit", session.id);
    navigate("session", { state: { session: session, id: session.id } });
  };

  const handleTrainingPlan = () => {
    console.log("Training Plan.", session.id);
  };

  const handleSeatingPlan = () => {
    console.log("Seating Plan", session.id);
  };

  const handleAttendance = () => {
    console.log("Attendance", session.id);
    navigate("attendance", { state: { session: session, id: session.id } });
  };

  return (
    <>
      <div className="flex justify-between gap-2 p-2 my-1 shadow-md shadow-orange-200/30 border border-gray-200 rounded min-w-lg">
        <article className="flex gap-4">
          <article onClick={handleEdit} className="flex gap-2">
            <section className="flex flex-col">
              <article className="flex gap-2">
                <p>{session.sessionType}</p>
                <p className="flex justify-end items-center text-orange-600 text-xs italic">
                  {session.craft}
                </p>
                <p className="flex justify-end items-center text-gray-500 text-xs italic">
                  {session.location}
                </p>
              </article>
              <p>{format(new Date(session.date), "PPPP")}</p>
            </section>
          </article>
          <section className="flex justify-center items-center gap-2">
            <p onClick={handleTrainingPlan} className="text-3xl">
              📒
            </p>
            <p onClick={handleSeatingPlan} className="flex text-3xl">
              <span>🪑</span>
              <span className="-ml-2">🪑</span>
            </p>
          </section>
        </article>
        <article
          onClick={handleAttendance}
          className="flex justify-center items-center gap-2 pl-10 text-2xl"
        >
          <p className="font-medium text-red-600">
            {
              session.presentAthletes?.filter((el) => el.gender === "FEMALE")
                .length
            }
          </p>
          <p className="font-medium text-blue-600">
            {
              session.presentAthletes?.filter((el) => el.gender === "MALE")
                .length
            }
          </p>
          <p onClick={handleDelete} className="text-2xl pl-2">
            🗑️
          </p>
        </article>
      </div>
    </>
  );
};

export default SessionCard;
