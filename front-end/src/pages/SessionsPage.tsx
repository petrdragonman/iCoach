import { useQuery } from "@tanstack/react-query";
import SessionCard from "../components/sessionCard/SessionCard";
import { getAllSessions } from "../services/SessionsService";

const SessionsPage = () => {
  const { data, isPending, error } = useQuery({
    queryKey: ["sessions"],
    queryFn: getAllSessions,
  });

  if (error) {
    alert("An error has accured while fetching data.");
  }

  return (
    <div>
      <header className="text-red-400 text-5xl">iCoach</header>
      <p>{isPending ? <h3>LOADING</h3> : JSON.stringify(data)}</p>
      <SessionCard></SessionCard>
    </div>
  );
};

export default SessionsPage;
