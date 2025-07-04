import { useLocation, useNavigate } from "react-router";
import type { SessionFormData } from "../components/sessionForm/schema";
import SessionForm from "../components/sessionForm/SessionForm";
import { useCreateSession, useUpdateSession } from "../services/queries";

const SessionPage = () => {
  const navigate = useNavigate();
  const createSessionMutation = useCreateSession();
  const updateSessionMutation = useUpdateSession();
  const { state } = useLocation();

  const onSubmit = (data: SessionFormData) => {
    console.log(data);
    const mutation = state?.session
      ? updateSessionMutation.mutateAsync({ ...data, id: state.id })
      : createSessionMutation.mutateAsync(data);
    mutation.then(() => {
      navigate("/");
    });
  };

  const onCancel = () => {
    navigate("/");
  };

  return (
    <main className="flex justify-center items-center w-screen mt-30">
      <SessionForm
        onSubmit={onSubmit}
        onCancel={onCancel}
        existingData={state?.session}
      />
    </main>
  );
};

export default SessionPage;
